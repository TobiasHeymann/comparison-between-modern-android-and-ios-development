import SwiftUI

struct HeaderComponent: View {
    
    private let header: String
    
    init(header: String) {
        self.header = header
    }
    
    var body: some View {
        Text(header)
            .padding(24)
            .modifier(MaterialTheme.typography.h1)
    }
}
