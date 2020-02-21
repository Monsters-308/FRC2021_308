package frc.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;
import edu.wpi.first.wpilibj.DigitalInput;
import frc.robot.Constants;
import frc.robot.subsystems.LiftSubsystem;

/**
 * A simple command that stops the lift with the {@link LiftSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ForwardLift extends CommandBase {
  // The subsystem the command runs on
  private final LiftSubsystem m_liftSubsystem;
  private DigitalInput m_liftSensor;

  public ForwardLift(LiftSubsystem subsystem) {
    m_liftSensor = new DigitalInput(Constants.LiftConstants.kLiftSensorPort);
    m_liftSubsystem = subsystem;
    addRequirements(m_liftSubsystem);
  }

  @Override
  public void initialize() {
    if(!m_liftSensor.get()){
      m_liftSubsystem.forwardLift();
    }
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
