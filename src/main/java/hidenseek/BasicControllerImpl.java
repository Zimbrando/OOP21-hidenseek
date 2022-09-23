package hidenseek;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class BasicControllerImpl implements BasicController {
    
    private Set<View> views = new HashSet<View>();
    
    @Override
    public void attach(View v) {
        this.views.add(v);
    }

    @Override
    public void detach(View v) {
        this.views.remove(v);
    }
    
    @Override
    public List<View> getAttachedViews() {
        return new ArrayList<View>(this.views.stream().toList());
    }
}
