package hidenseek.model.statistics.time;

import java.time.Duration;

import hidenseek.model.statistics.StatisticProperty;

public class Time extends StatisticProperty<Duration> {

    private Duration _duration;
    public Time(int seconds) {
        _duration = Duration.ofSeconds(seconds);
    }
    
    @Override
    public String XMLSerialize() {
        return Long.toString(_duration.toSeconds());
    }

    @Override
    public void XMLDeserialize(String value) {
        _duration = Duration.ofSeconds(Long.parseLong(value));
    }

    @Override
    public Duration getValue() {
        return _duration;
    }

    @Override
    public void setValue(Duration value) {
        _duration = value;
        saveStatistics();
    }
    
    public void addTime(long seconds) {
        setValue(Duration.ofSeconds(_duration.toSeconds() + seconds));
    }
    
    public void subtractTime(long seconds) {
        setValue(Duration.ofSeconds(Math.max(0, _duration.toSeconds() - seconds)));
    }
}
