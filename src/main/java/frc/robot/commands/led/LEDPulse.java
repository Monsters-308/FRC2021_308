package frc.robot.commands.led;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LEDSubsystem;;

/**
 * A simple command that controls leds with the {@link LEDSubsystem}. Written
 * explicitly for pedagogical purposes. Actual code should inline a command this
 * simple with {@link edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class LEDPulse extends CommandBase {
    // The subsystem the command runs on
    private final LEDSubsystem m_ledSubsystem;
    private double m_onTime;
    private double m_offTime;
    private double m_getFPGATimestamp;

    public LEDPulse(LEDSubsystem subsystem, double onTime, double offTime) {
        m_onTime = onTime;
        m_offTime = offTime;
        m_ledSubsystem = subsystem;
        addRequirements(m_ledSubsystem);
    }

    @Override
    public void initialize() {
        m_getFPGATimestamp = Timer.getFPGATimestamp();
    }

    @Override
    public boolean isFinished() {
        if (Timer.getFPGATimestamp() < m_getFPGATimestamp + m_onTime) {
            m_ledSubsystem.red();
        } else if (Timer.getFPGATimestamp() < m_getFPGATimestamp + m_onTime + m_offTime) {
            m_ledSubsystem.clearAll();
        } else {
            m_getFPGATimestamp = Timer.getFPGATimestamp(); // reset the timer
        }
        return true;
    }
}
