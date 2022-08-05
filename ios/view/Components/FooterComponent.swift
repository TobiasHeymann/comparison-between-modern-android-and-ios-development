import SwiftUI

struct FooterComponent: View {
    var body: some View {
        
        VStack(alignment: .center, spacing: 16) {
            NavigationLink(
                destination: AboutPage()
            ) {
                Text("Impressum")
                    .foregroundColor(MaterialTheme.colors.PRIMARY)
                    .modifier(MaterialTheme.typography.h2)
            }
            
            Text("© Tobias Heymann")
                .modifier(MaterialTheme.typography.h2)
        }
        .padding(.top, 64)
        .padding(.bottom, 24)
        .frame(
            minWidth: 0,
            maxWidth: .infinity
        )
    }
}

struct FooterComponent_Previews: PreviewProvider {
    static var previews: some View {
        FooterComponent()
    }
}
