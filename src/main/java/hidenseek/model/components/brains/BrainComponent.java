package hidenseek.model.components.brains;

import hidenseek.model.components.Component;

public interface BrainComponent extends Component{
    
    /**
     * Use components to retrieve information about the world,
     * then act according to the heart.
     */
    void neuroImpulse();
}
