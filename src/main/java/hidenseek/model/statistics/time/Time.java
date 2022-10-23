package hidenseek.model.statistics.time;

import java.time.Duration;

import hidenseek.model.statistics.StatisticProperty;

public class Time extends StatisticProperty<Duration> {

    private Duration duration;
    public Time(int seconds) {
        duration = Duration.ofSeconds(seconds);
    }
    
    @Override
    public String XMLSerialize() {
        return Long.toString(duration.toSeconds());
    }

    @Override
    public void XMLDeserialize(String value) {
        duration = Duration.ofSeconds(Long.parseLong(value));
    }

    @Override
    public Duration getValue() {
        return duration;
    }

    @Override
    public void setValue(Duration value) {
        duration = value;
        saveStatistics();
    }
    
    public void addTime(long seconds) {
        setValue(Duration.ofSeconds(duration.toSeconds() + seconds));
    }
    
    public void subtractTime(long seconds) {
        setValue(Duration.ofSeconds(Math.max(0, duration.toSeconds() - seconds)));
    }
}
