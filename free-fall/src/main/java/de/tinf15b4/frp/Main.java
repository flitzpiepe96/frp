package de.tinf15b4.frp;

import nz.sodium.Cell;
import nz.sodium.StreamSink;
import nz.sodium.Unit;
import nz.sodium.time.SecondsTimerSystem;
import nz.sodium.time.TimerSystem;

import javax.swing.*;

import de.tinf15b4.frp.swidgets.SLabel;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        setUpLogic();
        loop();
    }

    private static void setUpLogic() {
        JFrame frame = new JFrame("Continuous Time 2");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BoxLayout(frame.getContentPane(), BoxLayout.Y_AXIS));

        TimerSystem<Double> timerSystem = new SecondsTimerSystem();
        Cell<Double> time = timerSystem.time;

        SLabel passed = new SLabel(time.map(d -> "Time passed: " + d + "s"));
        frame.add(passed);

        SLabel speed = new SLabel(time.map(d -> 9.81*d).map(d -> "Speed: " + d + "m/s"));
        frame.add(speed);
        
        SLabel distance = new SLabel(time.map(d -> (1d/2d) * 9.81 * Math.pow(d, 2)).map(d -> "Distance: " + d + "m"));
        frame.add(distance);
        
        frame.setSize(400, 160);
        frame.setVisible(true);
    }

    private static void loop() throws InterruptedException {
        long systemSampleRate = 1000L;
        StreamSink<Unit> sMain = new StreamSink<>();
        while(true) {
            //jede Transaktion aktualisiert TimerSystem.time (Sodium spezifisch) -> send löst Transaktion aus
            sMain.send(Unit.UNIT);
            Thread.sleep(systemSampleRate);
        }
    }
}
