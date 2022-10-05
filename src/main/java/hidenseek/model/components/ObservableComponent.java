package hidenseek.model.components;

public interface ObservableComponent extends Component {

    void attachListener(TriggerComponent tc);
    
    void detachListener();
}
