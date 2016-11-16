unit Unit1;

interface

uses
  Winapi.Windows, Winapi.Messages, System.SysUtils, System.Variants, System.Classes, Vcl.Graphics,
  Vcl.Controls, Vcl.Forms, Vcl.Dialogs, Vcl.StdCtrls, Vcl.Menus, Vcl.ExtCtrls,
  Vcl.Mask, Vcl.Buttons;

type
  TForm1 = class(TForm)
    cbEstilo: TComboBox;
    MainMenu1: TMainMenu;
    aaaa1: TMenuItem;
    PopupMenu1: TPopupMenu;
    este1: TMenuItem;
    Abrir1: TMenuItem;
    Sair1: TMenuItem;
    Label1: TLabel;
    Edit1: TEdit;
    Memo1: TMemo;
    Button1: TButton;
    CheckBox1: TCheckBox;
    RadioButton1: TRadioButton;
    ListBox1: TListBox;
    Panel1: TPanel;
    BitBtn1: TBitBtn;
    SpeedButton1: TSpeedButton;
    MaskEdit1: TMaskEdit;
    TrayIcon1: TTrayIcon;
    procedure FormCreate(Sender: TObject);
    procedure cbEstiloChange(Sender: TObject);
  private
    { Private declarations }
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

uses
    Vcl.Themes;

{$R *.dfm}

procedure TForm1.cbEstiloChange(Sender: TObject);
begin
  TStyleManager.SetStyle(cbEstilo.Text);
end;

procedure TForm1.FormCreate(Sender: TObject);
var
  StyleName: string;
begin
  for StyleName in TStyleManager.StyleNames do
    cbEstilo.Items.Add(StyleName);

  cbEstilo.ItemIndex := cbEstilo.Items.IndexOf(TStyleManager.ActiveStyle.Name);
end;

end.
