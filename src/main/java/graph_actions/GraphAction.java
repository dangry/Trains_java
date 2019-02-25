package graph_actions;

import models.Train;

public interface GraphAction {

  int execute();

  void setData(Train train);
}
