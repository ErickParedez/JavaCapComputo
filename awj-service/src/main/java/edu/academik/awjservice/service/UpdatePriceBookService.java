package edu.academik.awjservice.service;

import java.math.BigDecimal;
import java.util.stream.Stream;

import org.eclipse.microprofile.config.inject.ConfigProperty;

import edu.academik.awjservice.dao.BookDao;
import edu.academik.awjservice.event.BookUpdated;
import edu.academik.awjservice.qualifier.PriceCalculator;
import edu.academik.awjservice.qualifier.PriceCalculatorHandler;
import edu.academik.awjservice.qualifier.PriceCalculatorType;
import edu.academik.awjservice.qualifier.PriceCalculatorTypeAnnotationLiteral;
import edu.academik.awjservice.request.UpdatePriceBookRequest;
import jakarta.ejb.Singleton;
import jakarta.enterprise.event.Event;
import jakarta.enterprise.inject.Any;
import jakarta.enterprise.inject.Instance;
import jakarta.inject.Inject;

@Singleton
public class UpdatePriceBookService {

    @Inject
    private BookDao bookDao;

    @Inject
    @Any
    private Instance<PriceCalculatorHandler> priceCalculatorHandlerInstance;

    @Inject
    private Event<BookUpdated> bookUpdateEvent;

    @Inject
    @ConfigProperty(name = "precio.porcentaje")
    private Double percentagePrice;

    public void update(UpdatePriceBookRequest request) {

        this.bookDao.findById(request.getBookId())
                .ifPresentOrElse(book -> {

                    var priceCalculatorType = Stream.of(PriceCalculatorType.values())
                            .filter(type -> type.getId() == request.getPriceCalculatorTypeId())
                            .findFirst()
                            .orElseThrow();

                    var handler = priceCalculatorHandlerInstance
                            .select(new PriceCalculatorTypeAnnotationLiteral(priceCalculatorType))
                            .get();

                    book.setPrice(handler.getPrice().multiply(BigDecimal.valueOf(this.percentagePrice)));

                    this.bookUpdateEvent.fire(BookUpdated.builder()
                            .bookId(book.getBookId())
                            .build());

                }, () -> {
                    throw new RuntimeException("Libro Id no existe");
                });

    }
}
