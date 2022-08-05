import SwiftUI

struct PlantListComponent: View {
    
    let plants: [Plant]
    
    init(plants: [Plant]) {
        self.plants = plants
    }
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 16) {
                ForEach(plants, id: \.self) { element in
                    PlantRecommendationComponent(plant: element)
                }
            }
            .padding(.horizontal, 24)
            .padding(.vertical, 2)
        }
    }
}

struct PlantRecommendationComponent: View {
    
    private let plant: Plant
    
    init(plant: Plant) {
        self.plant = plant
    }
    
    var body: some View {
        NavigationLink(destination: PlantDetailPage(plant: self.plant)) {
            VStack(alignment: .leading, spacing: 0) {
                AsyncImage(
                    urlString: plant.metadata.image,
                    placeholder: UIImage(named: "placeholder_square_large")
                )
                .frame(width: 192, height: 224)
                .cornerRadius(4)
                
                VStack(alignment: .leading) {
                    
                    Text(plant.metadata.title)
                        .modifier(MaterialTheme.typography.h3)
                        .lineLimit(1)
                    
                }
                .frame(height: 32)
                .padding(.horizontal, 8)
            }
            .frame(width: 192, height: 256)
            .background(MaterialTheme.colors.SECONDARY)
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
