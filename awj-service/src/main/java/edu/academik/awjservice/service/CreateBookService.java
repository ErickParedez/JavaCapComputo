package edu.academik.awjservice.service;

import edu.academik.awjservice.request.CreateBookRequest;
import edu.academik.awjservice.response.CreateBookResponse;

public interface CreateBookService {

    CreateBookResponse create(CreateBookRequest request);

}
