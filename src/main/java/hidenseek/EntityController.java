package hidenseek;

class EntityController extends BasicControllerImpl {
   
    void draw() {
        this.getAttachedViews().forEach(v -> v.update());
    }
}
