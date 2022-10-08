package hidenseek.model.components;

import hidenseek.model.events.Event;

public interface ObservableComponent extends Component {
     
    <E extends Event> void attachListener(Trigger<E> tc);
    
    void detachListener();
}
