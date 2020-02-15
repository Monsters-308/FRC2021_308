package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class HalveDriveSpeed extends CommandBase {
  private final DriveSubsystem m_drive;

  public HalveDriveSpeed(DriveSubsystem drive) {
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.setMaxOutput(0.5);
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.setMaxOutput(1);
  }
}
