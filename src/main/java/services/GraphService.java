package services;

import models.Graph;
import utils.exception.InvalidPathNameException;

public interface GraphService {

  Graph createGraphFromText(String graphText) throws InvalidPathNameException;
}
