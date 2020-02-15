package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

/**
 * A simple command that stops the intake with the {@link IntakeSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class StopIntake extends CommandBase {
  // The subsystem the command runs on
  private final IntakeSubsystem m_intakeSubsystem;

  public StopIntake(IntakeSubsystem subsystem) {
    m_intakeSubsystem = subsystem;
    addRequirements(m_intakeSubsystem);
  }

  @Override
  public void initialize() {
    m_intakeSubsystem.stopIntake();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
