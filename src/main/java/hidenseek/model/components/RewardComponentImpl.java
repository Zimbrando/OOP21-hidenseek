package hidenseek.model.components;

public class RewardComponentImpl extends AbstractComponent implements RewardComponent {

    final private int reward;
    
    public RewardComponentImpl(final int reward) {
        this.reward = reward;
    }
    
    @Override
    public int getReward() {
        return this.reward;
    }
}
