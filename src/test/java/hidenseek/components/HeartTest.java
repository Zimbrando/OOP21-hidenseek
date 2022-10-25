package hidenseek.components;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import hidenseek.model.components.RewardComponentImpl;
import hidenseek.model.components.hearts.EvilHeartComponentImpl;
import hidenseek.model.components.hearts.GoodHeartComponentImpl;
import hidenseek.model.components.hearts.HeartComponent;
import hidenseek.model.entities.AbstractEntity;
import hidenseek.model.entities.Entity;

class HeartTest {


    @Test
    void evilHatesGood() {
        // evil heart
        final Entity evil = new AbstractEntity(){};
        evil.attach(new EvilHeartComponentImpl());
        // good heart
        final Entity good = new AbstractEntity(){};
        good.attach(new GoodHeartComponentImpl());
        
        // evil checks
        assertTrue(evil.getComponent(HeartComponent.class).get().hates(good));
        assertFalse(evil.getComponent(HeartComponent.class).get().hates(evil));
        // good checks
        assertTrue(good.getComponent(HeartComponent.class).get().hates(evil));
        assertFalse(good.getComponent(HeartComponent.class).get().hates(good));
    }
    

    @Test
    void goodLovesReward() {
        // good heart
        final Entity good = new AbstractEntity(){};
        good.attach(new GoodHeartComponentImpl());
        // reward entity
        final Entity reward = new AbstractEntity(){};
        reward.attach(new RewardComponentImpl(1));
        
        assertTrue(good.getComponent(HeartComponent.class).get().loves(reward));
        assertFalse(good.getComponent(HeartComponent.class).get().hates(reward));
    }
    
    
}
