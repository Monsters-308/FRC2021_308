package frc.robot.commands.wof;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.WOFSubsystem;;

/**
 * A simple command that reverses the WOF with the {@link WOFSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ReverseWOF extends CommandBase {
  // The subsystem the command runs on
  private final WOFSubsystem m_wofSubsystem;

  public ReverseWOF(WOFSubsystem subsystem) {
    m_wofSubsystem = subsystem;
    addRequirements(m_wofSubsystem);
  }

  @Override
  public void initialize() {
    m_wofSubsystem.reverseWOF();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
