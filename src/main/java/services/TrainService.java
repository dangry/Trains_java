package services;

import actions.GraphAction;
import actions.GraphActionFactory;
import models.Action;
import models.Train;

import java.util.ArrayList;
import java.util.List;

public class TrainService {
    private final TextService textServiceImpl;

    public TrainService(TextService textServiceImpl) {
        this.textServiceImpl = textServiceImpl;
    }

    public List<String> doActions(List<Action> actionList) {

        List<String> responseList = new ArrayList<>();

        for (Action action : actionList) {
            String actionResponse;

            Train train = Train.createTrain(action, textServiceImpl.getGraph());

            GraphAction graphAction = GraphActionFactory.getAction(action.getActionType());
            graphAction.setData(train);

            int result = graphAction.execute();

            if (result > -1) {
                actionResponse = String.valueOf(result);
            } else {
                actionResponse = TextServiceImpl.NO_SUCH_ROUTE;
            }

            responseList.add(actionResponse);
        }

        return responseList;
    }
}