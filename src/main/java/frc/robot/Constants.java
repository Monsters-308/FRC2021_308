package frc.robot;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.TalonFXInvertType;

/**
 * The Constants class provides a convenient place for teams to hold robot-wide
 * numerical or boolean constants. This class should not be used for any other
 * purpose. All constants should be declared globally (i.e. public static). Do
 * not put anything functional in this class.
 *
 * <p>
 * It is advised to statically import this class (or one of its inner classes)
 * wherever the constants are needed, to reduce verbosity.
 */
public final class Constants {
  public static final class DriveConstants {
    public static final int kLeftMotor1Port = 12;
    public static final int kLeftMotor2Port = 13;
    public static final int kRightMotor1Port = 15;
    public static final int kRightMotor2Port = 14;


    public static final int kEncoderCPR = 1024;
    public static final double kWheelDiameterInches = 6;
    public static final double kEncoderDistancePerPulse =
        // Assumes the encoders are directly mounted on the wheel shafts
        (kWheelDiameterInches * Math.PI) / (double) kEncoderCPR;

    public static final int kLEDRingModule = 10;
    public static final int kLEDRingPort = 3;

  }

  public static final class IntakeConstants {
    public static final int kIntakeSolenoidModule = 10;
    public static final int kIntakeSolenoidPort = 0;
    public static final int kIntakeMotorCANPort = 1; //0
    public static final double kIntakeMotorForwardSpeed = 1.0;
    public static final double kIntakeMotorReverseSpeed = -0.5;
    public static final double kIntakeMotorStopSpeed = 0.0;
  }

  public static final class HopperConstants {
    public static final int kHopperMotorCANPort = 2; //1
    public static final double kHopperMotorForwardSpeed = 0.3;
    public static final double kHopperMotorReverseSpeed = -0.3;
    public static final double kHopperMotorStopSpeed = 0.0;
  }

  public static final class IndexerConstants {
    public static final int kIndexerMotorCANPort = 0; //2
    public static final double kIndexerMotorForwardSpeed = 1.0;
    public static final double kIndexerMotorReverseSpeed = -1.0;
    public static final double kIndexerMotorStopSpeed = 0.0;
    public static final int kShootSensorInput = 3;
    public static final int kBallIndexedSensorInput = 2;
    public static final int kBallReadySensorInput = 1;

  }

  public static final class ShooterConstants {
    public static final int kShooterMotorCANPort = 9;
    public static final double kShooterMotorForwardSpeed = 0.9;
    public static final double kShooterMotorMidSpeed = 0.75;
    public static final double kShooterMotorShortSpeed = 0.7;
    public static final double kShooterMotorStopSpeed = 0.0;
    public static final int kUnitsPerRevolution = 2048;
    public static final TalonFXInvertType kInvertType = TalonFXInvertType.CounterClockwise; // <<< What direction you want "forward/up" to be.
    public static final NeutralMode kBrakeDurNeutral = NeutralMode.Coast;
    public static final double kF = 0.05;
    public static final double kD = 5.0;
    public static final double kI = 0.001;
    public static final double kP = 0.1;
    public static final int kIzone = 300;
    public static final double kvelocityError = 100.0;
    public static final double kLongShotRPM = 5200.0;
    public static final double kMidShotRPM = 4300.0;
    public static final double kShortShotRPM = 4000.0;
  }

  public static final class LiftConstants {
    public static final int kLiftMotorCANPort = 4;
    public static final double kLiftMotorForwardSpeed = 1.0;
    public static final double kLiftMotorReverseSpeed = -1.0;
    public static final double kLiftMotorStopSpeed = 0.0;
    public static final int kLiftSensorPort = 4;
  }
  public static final class TraverseConstants {
    public static final int kTraverseMotorCANPort = 5;
    public static final double kTraverseMotorForwardSpeed = 0.5;
    public static final double kTraverseMotorReverseSpeed = -0.5;
    public static final double kTraverseMotorStopSpeed = 0.0;
  }

  public static final class WOFConstants {
    public static final int kWOFMotorCANPort = 3;
    public static final double kWOFMotorForwardSpeed = 0.3;
    public static final double kWOFMotorReverseSpeed = -0.3;
    public static final double kWOFMotorStopSpeed = 0.0;
    public static final double kWOFMotorSlowForwardSpeed = 0.1;
    public static final double kWOFMotorSlowReverseSpeed = -0.1;
  }

  public static final class AutoConstants {
    public static final double kAutoDriveDistanceInches = 60;
    public static final double kAutoBackupDistanceInches = 20;
    public static final double kAutoDriveSpeed = 0.2;
    public static final double kAutoDriveTime = 3.0;
  }

  public static final class OIConstants {
    public static final int kDriverControllerPort = 1;
    public static final int kCoDriverControllerPort = 2;
  }

  public static final class LEDConstants {
    public static final int kLEDPWMPort = 0;
    public static final int kLEDCount = 82;
  }
}
