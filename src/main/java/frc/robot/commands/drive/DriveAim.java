/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands.drive;

import java.util.function.DoubleSupplier;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Constants;
import frc.robot.subsystems.DriveSubsystem;


public class DriveAim extends CommandBase {
  private final DriveSubsystem m_drive;
  private final DoubleSupplier m_forward;
  // private double m_targetYawError;
  private double KpRot = -0.1;
  private double rotationError;
  private double angleTolerance = 3.0;
  private double rotationAdjust;
  //TODO need to find the constantForce
  private double constantForce = 0.05;


  /**
   * Creates a new DriveAim.
   */
  public DriveAim(DriveSubsystem subsystem, DoubleSupplier forward) {
    m_forward = forward;
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
    m_drive.setDriverMode(false);
    rotationAdjust = 0;
    rotationError = m_drive.getVisionYaw();
    if(rotationError > 0.1){
      rotationAdjust = KpRot*rotationError+constantForce;
    }else if(rotationError <-0.1){
        rotationAdjust = KpRot*rotationError-constantForce;
    }else{
      rotationAdjust = 0;
    }
    if(rotationAdjust > 0.3){
      rotationAdjust = 0.3;
    }
    if(rotationAdjust < -0.3){
      rotationAdjust = -0.3;
    }

    m_drive.arcadeDrive(m_forward.getAsDouble()/-1.05,-rotationAdjust);

  }


  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {
   m_drive.setDriverMode(false);
   m_drive.setLedState(false);    
  }

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
