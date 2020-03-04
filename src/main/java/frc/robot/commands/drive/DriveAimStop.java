/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveSubsystem;


public class DriveAimStop extends CommandBase {
  private final DriveSubsystem m_drive;
  // private double m_targetYawError;
  private double KpRot = -0.1;
  private double rotationError;
//  private double angleTolerance = 3.0;
  private double rotationAdjust;
  //TODO need to find the constantForce
  private double constantForce = 0.05;
  /**
   * Creates a new DriveAim.
   */
  public DriveAimStop(DriveSubsystem subsystem) {
    m_drive = subsystem;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {
    m_drive.setDriverMode(false);
    m_drive.setLedState(true);
  }

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
    m_drive.setDriverMode(false);
    m_drive.setLedState(false);// turn off LED
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    m_drive.setDriverMode(false);
    rotationAdjust = 0;
    rotationError = m_drive.getVisionYaw() +5;
    if(rotationError > 0.2){
      rotationAdjust = KpRot*rotationError+constantForce;
    }else if(rotationError <-0.2){
        rotationAdjust = KpRot*rotationError-constantForce;
    }else{
      rotationAdjust = 0;
    }

    if(rotationAdjust != 0){
      if(rotationAdjust > 0 && rotationAdjust < 0.15){
        rotationAdjust = 0.2;
      }
      if(rotationAdjust < 0 && rotationAdjust > -0.15){
        rotationAdjust = -0.2;
      }
    }
    
    if(rotationAdjust > 0.3){
      rotationAdjust = 0.3;
    }
    if(rotationAdjust < -0.3){
      rotationAdjust = -0.3;
    }

    if(Math.abs(rotationError)<0.1){
        m_drive.arcadeDrive(0.0,0.0);
        return true;
    }else{
        m_drive.arcadeDrive(0.0,-rotationAdjust);
        return false;    
    }
  }
}