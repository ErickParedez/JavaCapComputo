package edu.academik.awjservice.domain;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "library")
@Getter
@Setter
@ToString(of = "libraryId")
@EqualsAndHashCode(of = "libraryId")
public class Library implements Serializable {

    @Id
    @Column(name = "library_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long libraryId;

    @Column(name = "name")
    private String name;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id", referencedColumnName = "address_id", insertable = false, updatable = false)
    private Address address;

    @Column(name = "address_id")
    private Long addressId;

    @OneToMany(mappedBy = "library")
    private List<Book> bookList;
}
