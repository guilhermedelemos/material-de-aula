Function GetAddress(HyperlinkCell As Range)
    GetAddress = Replace(HyperlinkCell.Hyperlinks(1).Address, "mailto:", "")
End Function