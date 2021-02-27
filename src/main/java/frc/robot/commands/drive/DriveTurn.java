package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;

import frc.robot.subsystems.DriveSubsystem;

public class DriveTurn extends CommandBase {
  private final DriveSubsystem m_drive;
  private final double m_heading;
  private final double m_speed;
  private double m_speed2;
  private final double m_headingError =1.5;

  /**
   * Creates a new DriveDistance.
   *
   * @param heading The heading to turn too
   * @param speed The speed at which the robot will drive
   * @param drive The drive subsystem on which this command will run
   */
  public DriveTurn(double heading, double speed, DriveSubsystem drive) {
    m_heading = heading;
    m_speed = speed;
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
        if(Math.abs(m_drive.getGyroHeading()) < Math.abs(m_heading)){
          m_speed2 = (Math.abs(m_heading-m_drive.getGyroHeading()/m_heading));
          if(m_speed < 0){
            m_speed2 = -m_speed2;
          }
            if(m_speed2 > m_speed){
              m_speed2 = m_speed;
            }
          
          else if(m_speed2 < m_speed){
            m_speed2 = m_speed;
          }
        }
        m_drive.arcadeDrive(0.0, m_speed2);
        return false;
        
      }

  }
  }

