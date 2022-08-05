import SwiftUI

struct AboutPage: View {
    
    @Environment(\.presentationMode) var presentation
        
    var body: some View {
        ZStack(alignment: .leading) {
            MaterialTheme.colors.BACKGROUND.edgesIgnoringSafeArea(.all)
            
            ScrollView() {
                VStack(alignment: .leading) {
                    HStack(alignment: .center, spacing: 24) {
                        Group {
                            Image("baseline_arrow_back_black_24pt")
                                .resizable()
                                .frame(width: 20, height: 20)
                                .foregroundColor(Color.white)
                        }
                        .frame(width: 32, height: 32, alignment: .center)
                        .background(MaterialTheme.colors.PRIMARY)
                        .cornerRadius(4)
                        .onTapGesture {
                            self.presentation.wrappedValue.dismiss()
                        }
                        
                        Text("Impressum")
                            .modifier(MaterialTheme.typography.h1)
                    }.padding(.all, 24)
                    
                    
                    /* About */
                    ContentHeader(header: "Adresse")
                    Text("Tobias Heymann")
                        .modifier(MaterialTheme.typography.body1)
                        .padding(.horizontal, 24)
                        .padding(.top, 6)
                    Text("Musterstraße 1")
                        .modifier(MaterialTheme.typography.body1)
                        .padding(.horizontal, 24)
                    Text("Musterstadt")
                        .modifier(MaterialTheme.typography.body1)
                        .padding(.horizontal, 24)
                    
                    Rectangle()
                        .fill(MaterialTheme.colors.TEXT_SECONDARY)
                        .frame(height: 1)
                        .padding([.leading, .top, .trailing], 24)
                        .padding(.bottom, 12)
                    
                    /* Open Source Licences */
                    Text("Open Source Lizenzen")
                        .modifier(MaterialTheme.typography.h1)
                        .padding(.horizontal, 24)
                        .padding(.vertical, 12)
                    
                    ContentHeader(header: "CodeScanner")
                    ContentText(
                        text: "MIT License. Copyright (c) 2019 Paul Hudson\n\nPermission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files (the \"Software\"), to deal in the Software without restriction, including without limitation the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, and to permit persons to whom the Software is furnished to do so, subject to the following conditions:\n\nThe above copyright notice and this permission notice shall be included in all copies or substantial portions of the Software.\n\nTHE SOFTWARE IS PROVIDED \"AS IS\", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE."
                    )
                    
                }
            }
        }
        .navigationBarTitle("")
        .navigationBarHidden(true)
    }
}
