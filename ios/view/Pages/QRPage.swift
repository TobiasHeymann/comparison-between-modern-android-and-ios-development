import SwiftUI
import CodeScanner


struct QRPage: View {
    
    @Environment(\.presentationMode) var presentation
    
    @State private var plant: Plant?
    
    var body: some View {
        ZStack(alignment: .topLeading) {
            CodeScannerView(codeTypes: [.qr], simulatedData: "63158da6-f38c-4652-a400-8665126075f2", completion: self.handleScan)
                .background(Color.white)
                .edgesIgnoringSafeArea(.all)
            
            ZStack(alignment: .top) {
                Image("background_curve")
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        alignment: .top
                    )
                    .rotationEffect(.degrees(180))
                    .foregroundColor(MaterialTheme.colors.BACKGROUND)
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .top)
            .edgesIgnoringSafeArea(.all)
                        
            ZStack(alignment: .bottom) {
                Image("background_curve")
                    .frame(
                        minWidth: 0,
                        maxWidth: .infinity,
                        alignment: .bottom
                    )
                    .foregroundColor(MaterialTheme.colors.BACKGROUND)
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .bottom)
            .edgesIgnoringSafeArea(.all)
                        
            ZStack(alignment: .center) {
                Image("background_qr")
                    .frame(
                        width: 200,
                        height: 200,
                        alignment: .center
                    )
                    .foregroundColor(Color.white)
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .center)
            .edgesIgnoringSafeArea(.all)
            
            ZStack {
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
            }
            .padding(.all, 16)
            
            ZStack(alignment: .bottom) {
                if plant != nil {
                    SearchResultComponent(plant: plant!)
                        .padding(.horizontal, 24)
                }
            }
            .frame(minWidth: 0, maxWidth: .infinity, minHeight: 0, maxHeight: .infinity, alignment: .bottom)
        }
        .navigationBarTitle("")
        .navigationBarHidden(true)
    }
    
    func handleScan(result: Result<String, CodeScannerView.ScanError>) {
        switch result {
            case .success(let code):
                
                if let id = UUID(uuidString: code) {
                    DatabaseService.searchPlantById(uuid: id.description.lowercased()) { result in
                        self.plant = result
                    }
                }
                
            case .failure(let error):
                print(error)
        }
    }
}
