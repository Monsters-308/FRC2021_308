package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.IntakeSubsystem;

/**
 * A simple command that runs intake forward with the {@link IntakeSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class PerpetualIntake extends CommandBase {
  // The subsystem the command runs on
  private final IntakeSubsystem m_intakeSubsystem;

  public PerpetualIntake(IntakeSubsystem subsystem) {
    m_intakeSubsystem = subsystem;
    addRequirements(m_intakeSubsystem);
  }

  @Override
  public void initialize() {
    m_intakeSubsystem.forwardIntake();
  }

  @Override
  public boolean isFinished() {
    return false;
  }
}
