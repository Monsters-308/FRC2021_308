package frc.robot.commands.lift;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.LiftTraverseSubsystem;

/**
 * A command to drive the robot with joystick input (passed in as {@link DoubleSupplier}s). Written
 * explicitly for pedagogical purposes - actual code should inline a command this simple with {@link
 * edu.wpi.first.wpilibj2.command.RunCommand}.
 */
public class DefaultLift extends CommandBase {
  private final LiftTraverseSubsystem m_drive;
  private final DoubleSupplier m_lift;
  private final DoubleSupplier m_traverse;

  /**
   * Creates a new DefaultDrive.
   *
   * @param subsystem The drive subsystem this command wil run on.
   * @param forward The control input for driving forwards/backwards
   * @param rotation The control input for turning
   */
  public DefaultLift(LiftTraverseSubsystem subsystem, DoubleSupplier lift, DoubleSupplier traverse) {
    m_drive = subsystem;
    m_lift = lift;
    m_traverse = traverse;
    addRequirements(m_drive);
  }

  @Override
  public void execute() {
      m_drive.lift(m_lift.getAsDouble(),m_traverse.getAsDouble());
  }
}