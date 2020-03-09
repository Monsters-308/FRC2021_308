package frc.robot.commands.wof;

import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj2.command.CommandBase;
//import edu.wpi.first.wpilibj.util.Color;
//import com.revrobotics.ColorSensorV3;
import frc.robot.subsystems.WOFSubsystem;;

/**
 * A simple command that reverses the WOF with the {@link WOFSubsystem}. Written
 * explicitly for pedagogical purposes. Actual code should inline a command this
 * simple with {@link edu.wpi.first.wpilibj2.command.InstantCommand}.
 */
//public class WOFSpinSpecific extends CommandBase {
  // The subsystem the command runs on
  //private final WOFSubsystem m_wofSubsystem;
  /*private final I2C.Port i2cport = I2C.Port.kOnboard;
  private final ColorSensorV3 m_colorsensor = new ColorSensorV3(i2cport);
  int proximity = m_colorsensor.getProximity();
  boolean WOF_Yellow = false;
  boolean WOF_Red = false;
  boolean WOF_Green = false;
  boolean WOF_Blue = false;
  public Color detectedColor;

  public WOFSpinSpecific(WOFSubsystem subsystem) {
    m_wofSubsystem = subsystem;
    addRequirements(m_wofSubsystem);
  }

  @Override
  public void initialize() {
  }

  @Override
  public void execute() {
  }

  @Override
  public boolean isFinished() {
    detectedColor = m_colorsensor.getColor();
    if ((detectedColor.red <= 0.5) && (detectedColor.red >= 0.35) && (detectedColor.green >= 0.45)) {
      WOF_Yellow = true;
    } else {
      WOF_Yellow = false;
    }
    if (detectedColor.red >= 0.55) {
      WOF_Red = true;
    } else {
      WOF_Red = false;
    }
    if ((detectedColor.blue >= 0.34) && (detectedColor.green >= 0.4)) {
      WOF_Blue = true;
    } else {
      WOF_Blue = false;
    }
    if (detectedColor.green >= 0.55) {
      WOF_Green = true;
    } else {
      WOF_Green = false;
    }
    String gameData;
    gameData = DriverStation.getInstance().getGameSpecificMessage();
    if (gameData.length() > 0) {
      switch (gameData.charAt(0)) {
      case 'B':
        if (WOF_Blue == false) {
          m_wofSubsystem.slowForwardWOF();
        } else if (WOF_Blue == true) {
          m_wofSubsystem.stopWOF();
          return true;
        }
        break;
      case 'G':
        if (WOF_Green == false) {
          m_wofSubsystem.slowForwardWOF();
        } else if (WOF_Green == true) {
          m_wofSubsystem.stopWOF();
          return true;
        }
        break;
      case 'R':
        if (WOF_Red == false) {
          m_wofSubsystem.slowForwardWOF();
        } else if (WOF_Red == true) {
          m_wofSubsystem.stopWOF();
          return true;
        }
        break;
      case 'Y':
        if (WOF_Yellow == false) {
          m_wofSubsystem.slowForwardWOF();
        } else if (WOF_Yellow == true) {
          m_wofSubsystem.stopWOF();
          return true;
        }
        break;
      }
    }
    return false;
  }
}
*/