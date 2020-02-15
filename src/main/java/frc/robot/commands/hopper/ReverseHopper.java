package frc.robot.commands.hopper;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.HopperSubsystem;;

/**
 * A simple command that reverses the hopper with the {@link HopperSubsystem}.  Written explicitly for
 * pedagogical purposes.  Actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
public class ReverseHopper extends CommandBase {
  // The subsystem the command runs on
  private final HopperSubsystem m_hopperSubsystem;

  public ReverseHopper(HopperSubsystem subsystem) {
    m_hopperSubsystem = subsystem;
    addRequirements(m_hopperSubsystem);
  }

  @Override
  public void initialize() {
    m_hopperSubsystem.reverseHopper();
  }

  @Override
  public boolean isFinished() {
    return true;
  }
}
