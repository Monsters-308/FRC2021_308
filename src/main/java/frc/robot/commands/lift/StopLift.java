package frc.robot.commands.lift;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LiftSubsystem;;

/**
 * A simple command that stops the lift with the {@link LiftSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class StopLift extends CommandBase {
  // The subsystem the command runs on
  private final LiftSubsystem m_liftSubsystem;

  public StopLift(LiftSubsystem subsystem) {
    m_liftSubsystem = subsystem;
    addRequirements(m_liftSubsystem);
  }

  @Override
  public void initialize() {
    m_liftSubsystem.stopLift();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
