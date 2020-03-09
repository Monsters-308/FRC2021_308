package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;
// import frc.robot.Constants.DriveConstants;
import frc.robot.Constants.LiftConstants;
import frc.robot.Constants.TraverseConstants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
// import com.revrobotics.CANSparkMax;
// import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;
// import com.revrobotics.CANEncoder;

public class LiftTraverseSubsystem extends SubsystemBase {
  // The motors on the left side of the drive.
  private final WPI_TalonSRX m_lift = new WPI_TalonSRX(LiftConstants.kLiftMotorCANPort);
  private final WPI_TalonSRX m_traverse = new WPI_TalonSRX(TraverseConstants.kTraverseMotorCANPort);
  private DigitalInput m_liftLimit = new DigitalInput(Constants.LiftConstants.kLiftSensorPort);

  /**
   * Creates a new DriveSubsystem.
   */
  public LiftTraverseSubsystem() {
    //   m_lift.setInverted(true);
      m_lift.setNeutralMode(NeutralMode.Brake);
      m_traverse.setNeutralMode(NeutralMode.Brake);

  }

  /**
   * Moves the Lift and Traverse.
   *
   * @param lift the commanded forward movement
   * @param traverse the commanded rotation
   */
  public void lift(double lift, double traverse) {
      lift = lift * -1;
      if(lift > 0 && m_liftLimit.get()){
          m_lift.set(lift);
      }else if(lift > 0 && !m_liftLimit.get()){
          m_lift.set(0.0);
      }else if(lift <= 0){
          m_lift.set(lift);
      }
      m_traverse.set(traverse);
  }


  @Override
  public void periodic(){
    SmartDashboard.putBoolean("New Lift Limit",m_liftLimit.get());
  }


}
