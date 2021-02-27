package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveWideTurn extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_heading;
  private final double m_speed;
  private final double m_turnSpeed;
  private final double m_headingError = 10.0;

  /**
   * Creates a new DriveDistance.
   *
   * @param heading The heading to turn too
   * @param speed The speed at which the robot will drive
   * @param turnSpeed The speed at which the robot will turn
   * @param drive The drive subsystem on which this command will run
   */
  public DriveWideTurn(double heading, double speed, double turnSpeed, DriveSubsystem drive) {
    m_heading = heading;
    m_speed = speed;
    m_turnSpeed = turnSpeed;
    m_drive = drive;
  }

  @Override
  public void initialize() {
    m_drive.resetEncoders();
    m_drive.resetGyro();
  }

  @Override
  public void end(boolean interrupted) {
    m_drive.arcadeDrive(0, 0);
    m_drive.resetGyro();
    m_drive.resetEncoders();
  }

  @Override
  public boolean isFinished() {
      if(m_drive.getGyroHeading() > m_heading - m_headingError && m_drive.getGyroHeading() < m_heading + m_headingError){
          return true;
      }else{
        m_drive.arcadeDrive(m_turnSpeed,m_speed);
        return false;
      }

  }
}
