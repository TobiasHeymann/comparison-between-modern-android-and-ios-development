import Foundation
import SwiftUI

class Typorgraphy {
    let h1 = H1ViewModifier()
    let h2 = H2ViewModifier()
    let h3 = H3ViewModifier()
    let subtitle1 = Subtitle1ViewModifier()
    let body1 = Body1ViewModifier()
    let body2 = Body2ViewModifier()
}

struct H1ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.TEXT_PRIMARY)
            .font(Font.custom("OpenSans-Bold", size: 20))
    }
}

struct H2ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.TEXT_PRIMARY)
            .font(Font.custom("OpenSans-Bold", size: 12))
    }
}

struct H3ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.PRIMARY_VARIABLE)
            .font(Font.custom("OpenSans-Bold", size: 12))
    }
}

struct Subtitle1ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.TEXT_SECONDARY)
            .font(Font.custom("OpenSans-Regular", size: 8))
    }
}

struct Body1ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.TEXT_PRIMARY)
            .font(Font.custom("OpenSans-Regular", size: 12))
    }
}

struct Body2ViewModifier: ViewModifier {
    let colors = Colors()
    
    func body(content: Self.Content) -> some View {
        content
            .foregroundColor(colors.TEXT_SECONDARY)
            .font(Font.custom("OpenSans-Regular", size: 12))
    }
}
