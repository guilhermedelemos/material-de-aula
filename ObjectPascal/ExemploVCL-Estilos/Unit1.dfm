object Form1: TForm1
  Left = 0
  Top = 0
  Caption = 'Form1'
  ClientHeight = 242
  ClientWidth = 527
  Color = clBtnFace
  Font.Charset = DEFAULT_CHARSET
  Font.Color = clWindowText
  Font.Height = -11
  Font.Name = 'Tahoma'
  Font.Style = []
  FormStyle = fsMDIForm
  Menu = MainMenu1
  OldCreateOrder = False
  PopupMenu = PopupMenu1
  OnCreate = FormCreate
  PixelsPerInch = 96
  TextHeight = 13
  object Label1: TLabel
    Left = 16
    Top = 40
    Width = 31
    Height = 13
    Caption = 'Label1'
  end
  object SpeedButton1: TSpeedButton
    Left = 432
    Top = 63
    Width = 23
    Height = 22
  end
  object cbEstilo: TComboBox
    Left = 53
    Top = 9
    Width = 145
    Height = 22
    Style = csOwnerDrawFixed
    TabOrder = 0
    OnChange = cbEstiloChange
  end
  object Edit1: TEdit
    Left = 53
    Top = 37
    Width = 121
    Height = 21
    TabOrder = 1
    Text = 'Edit1'
  end
  object Memo1: TMemo
    Left = 53
    Top = 64
    Width = 185
    Height = 89
    Lines.Strings = (
      'Memo1')
    TabOrder = 2
  end
  object Button1: TButton
    Left = 53
    Top = 159
    Width = 75
    Height = 25
    Caption = 'Button1'
    TabOrder = 3
  end
  object CheckBox1: TCheckBox
    Left = 256
    Top = 11
    Width = 97
    Height = 17
    Caption = 'CheckBox1'
    TabOrder = 4
  end
  object RadioButton1: TRadioButton
    Left = 256
    Top = 34
    Width = 113
    Height = 17
    Caption = 'RadioButton1'
    TabOrder = 5
  end
  object ListBox1: TListBox
    Left = 256
    Top = 64
    Width = 121
    Height = 97
    ItemHeight = 13
    TabOrder = 6
  end
  object Panel1: TPanel
    Left = 334
    Top = 193
    Width = 185
    Height = 41
    Caption = 'Panel1'
    TabOrder = 7
  end
  object BitBtn1: TBitBtn
    Left = 432
    Top = 32
    Width = 75
    Height = 25
    Caption = 'BitBtn1'
    TabOrder = 8
  end
  object MaskEdit1: TMaskEdit
    Left = 398
    Top = 104
    Width = 121
    Height = 21
    TabOrder = 9
    Text = 'MaskEdit1'
  end
  object MainMenu1: TMainMenu
    Left = 320
    Top = 144
    object aaaa1: TMenuItem
      Caption = 'Arquivo'
      object Abrir1: TMenuItem
        Caption = 'Abrir'
      end
      object Sair1: TMenuItem
        Caption = 'Sair'
      end
    end
  end
  object PopupMenu1: TPopupMenu
    Left = 152
    Top = 120
    object este1: TMenuItem
      Caption = 'Teste'
    end
  end
  object TrayIcon1: TTrayIcon
    Left = 384
    Top = 8
  end
end
