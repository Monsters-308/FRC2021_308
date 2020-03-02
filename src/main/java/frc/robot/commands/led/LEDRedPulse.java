package frc.robot.commands.led;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LEDSubsystem;;

/**
 * A simple command that controls leds with the {@link LEDSubsystem}. Written
 * explicitly for pedagogical purposes. Actual code should inline a command this
 * simple with {@link edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class LEDRedPulse extends CommandBase {
    // The subsystem the command runs on
    private final LEDSubsystem m_ledSubsystem;

    public LEDRedPulse(LEDSubsystem subsystem) {
        m_ledSubsystem = subsystem;
        addRequirements(m_ledSubsystem);
    }

    @Override
    public void initialize() {
        m_ledSubsystem.setRedPulse();
    }

    @Override
    public boolean isFinished() {
        return true;
    }
}
