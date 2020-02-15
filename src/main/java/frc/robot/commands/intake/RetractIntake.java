package frc.robot.commands.intake;

import edu.wpi.first.wpilibj2.command.InstantCommand;

import frc.robot.subsystems.IntakeSubsystem;

/**
 * A command that retracts the intake.
 */
public class RetractIntake extends InstantCommand {
  public RetractIntake(IntakeSubsystem subsystem) {
    super(subsystem::retractIntake, subsystem);
  }
}
