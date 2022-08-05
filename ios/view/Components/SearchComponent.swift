import SwiftUI

struct SearchComponent: View {
    
    @Binding var text: String
    
    var body: some View {
        Group {
            HStack(alignment: .center, spacing: 0) {
                
                Group {
                    Image(
                        text.isEmpty
                            ? "round_search_black_24pt"
                            : "round_arrow_back_black_24pt"
                    )
                    .frame(width: 20, height: 20)
                    .foregroundColor(MaterialTheme.colors.TEXT_PRIMARY)
                    .onTapGesture {
                        self.text = ""
                        UIApplication.shared.sendAction(
                            #selector(UIResponder.resignFirstResponder),
                            to: nil,
                            from: nil,
                            for: nil
                        )
                    }
                }
                .frame(width: 36, height: 36, alignment: .center)
                
                TextField("Suchen ...", text: self.$text)
                    .font(Font.custom("OpenSans-Regular", size: 12))
                    .padding(.horizontal, 4)
                
                NavigationLink(destination: QRPage()) {
                    Group {
                        Image("round_qr_code_black_24pt")
                            .frame(width: 20, height: 20)
                            .foregroundColor(Color.white)
                    }
                    .frame(width: 36, height: 36, alignment: .center)
                    .background(MaterialTheme.colors.PRIMARY)
                    .cornerRadius(4)
                }
                
            }
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                alignment: .leading
            )
            .padding(.all, 8)
            .background(Color.white)
            .cornerRadius(4)
            .shadow(
                color: Color.black.opacity(0.1),
                radius: 2,
                x: 1,
                y: 1
            )
        }
        .padding(.leading, 24)
        .padding(.top, 24)
        .padding(.trailing, 24)
    }
}

struct SearchResultListComponent: View {
    
    private let plants: [Plant]
    
    init(plants: [Plant]) {
        self.plants = plants
    }
    
    var body: some View {
        VStack(spacing: 16) {
            ForEach(plants, id: \.self) { element in
                SearchResultComponent(plant: element)
            }
        }.padding(.horizontal, 24)
    }
}

struct SearchResultComponent: View {
    
    private let plant: Plant
    
    init(plant: Plant) {
        self.plant = plant
    }
    
    var body: some View {
        NavigationLink(destination: PlantDetailPage(plant: self.plant)) {
            HStack(alignment: .top, spacing: 0) {
                AsyncImage(
                    urlString: plant.metadata.image,
                    placeholder: UIImage(named: "placeholder_square")
                )
                .frame(width: 116, height: 116)
                .background(Color.red)
                .cornerRadius(4)
                
                VStack(alignment: .leading) {
                
                    Text(plant.metadata.title)
                        .modifier(MaterialTheme.typography.h2)
                    
                    Text(plant.metadata.subtitle)
                        .modifier(MaterialTheme.typography.subtitle1)
                    
                    Text(plant.firstText)
                        .modifier(MaterialTheme.typography.body1)
                        .padding(.top, 4)
                        .lineLimit(3)
                }
                .padding(12)
            }
            .frame(
                minWidth: 0,
                maxWidth: .infinity,
                alignment: .leading
            )
            .background(Color.white)
            .cornerRadius(4)
            .shadow(
                color: Color.black.opacity(0.1),
                radius: 2,
                x: 1,
                y: 1
            )
        }
    }
}
