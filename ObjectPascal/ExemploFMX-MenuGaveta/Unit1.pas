unit Unit1;

interface

uses
  System.SysUtils, System.Types, System.UITypes, System.Classes, System.Variants,
  FMX.Types, FMX.Controls, FMX.Forms, FMX.Graphics, FMX.Dialogs, FMX.ListBox,
  FMX.Layouts, FMX.StdCtrls, FMX.Controls.Presentation, FMX.MultiView;

type
  TForm1 = class(TForm)
    MultiView1: TMultiView;
    Layout: TLayout;
    ToolBar: TToolBar;
    Button1: TButton;
    ListBox1: TListBox;
    ListBoxItem1: TListBoxItem;
    ListBoxItem2: TListBoxItem;
    LayoutContainer: TLayout;
    procedure FormCreate(Sender: TObject);
    procedure ListBoxItem1Click(Sender: TObject);
    procedure ListBoxItem2Click(Sender: TObject);
  private
    { Private declarations }
    FActiveForm: TForm;
    procedure FormOpen(aForm: TComponentClass);
  public
    { Public declarations }
  end;

var
  Form1: TForm1;

implementation

{$R *.fmx}

uses Unit2, Unit3;

procedure TForm1.FormCreate(Sender: TObject);
begin
  FActiveForm := nil;
end;

procedure TForm1.FormOpen(aForm: TComponentClass);
var
  i: Integer;
begin
  if (FActiveForm = nil) or (Assigned(FActiveForm) and
    (FActiveForm.ClassName <> aForm.ClassName) ) then
  begin
    for i := LayoutContainer.ControlsCount -1 downto 0 do
      LayoutContainer.RemoveObject(LayoutContainer.Controls[i]);

    FActiveForm.DisposeOf;
    FActiveForm := nil;

    Application.CreateForm(aForm, FActiveForm);
    LayoutContainer.AddObject(TLayout(FActiveForm.FindComponent('LayoutClient')));
  end;

end;

procedure TForm1.ListBoxItem1Click(Sender: TObject);
begin
  FormOpen(TForm2);
  {$IFDEF MSWINDOWS}
  MultiView1.HideMaster;
  {$ENDIF}
end;

procedure TForm1.ListBoxItem2Click(Sender: TObject);
begin
  FormOpen(TForm3);
  {$IFDEF MSWINDOWS}
  MultiView1.HideMaster;
  {$ENDIF}
end;

end.
