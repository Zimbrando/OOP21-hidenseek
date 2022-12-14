package hidenseek.model.statistics.time;

import java.time.Duration;

import hidenseek.model.statistics.StatisticProperty;

public class Time extends StatisticProperty<Duration> {

    private Duration duration;
    public Time(final int seconds) {
        duration = Duration.ofSeconds(seconds);
    }
    
    @Override
    public String XMLSerialize() {
        return Long.toString(duration.toSeconds());
    }

    @Override
    public void XMLDeserialize(final String value) {
        duration = Duration.ofSeconds(Long.parseLong(value));
    }

    @Override
    public Duration getValue() {
        return duration;
    }

    @Override
    public void setValue(final Duration value) {
        duration = value;
        saveStatistics();
    }
    
    public void addTime(final long seconds) {
        setValue(Duration.ofSeconds(duration.toSeconds() + seconds));
    }
    
    public void subtractTime(final long seconds) {
        setValue(Duration.ofSeconds(Math.max(0, duration.toSeconds() - seconds)));
    }

    @Override
    public String toString() {
        return String.format("%1$sh %2$sm", Integer.toString((int)Math.floor(duration.toHours())), Integer.toString(duration.toMinutesPart()));
    }
    
}
