package services;

import models.Graph;
import exceptions.InvalidPathNameException;

public interface GraphService {

  Graph createGraphFromText(String graphText) throws InvalidPathNameException;
}
