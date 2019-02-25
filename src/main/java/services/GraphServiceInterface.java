package services;

import models.Graph;
import utils.exception.InvalidPathNameException;

public interface GraphServiceInterface {

  Graph createGraphFromText(String graphText) throws InvalidPathNameException;
}
