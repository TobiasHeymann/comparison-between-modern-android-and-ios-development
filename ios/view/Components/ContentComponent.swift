import SwiftUI

struct ContentHeader: View {
    
    private let header: String
    
    init(header: String) {
        self.header = header
    }
    
    var body: some View {
        Text(header)
            .modifier(MaterialTheme.typography.h2)
            .padding(.horizontal, 24)
            .padding(.vertical, 6)
    }
}

struct ContentText: View {
    
    private let text: String
    
    init(text: String) {
        self.text = text
    }
    
    var body: some View {
        Text(text)
            .modifier(MaterialTheme.typography.body1)
            .padding(.horizontal, 24)
            .padding(.vertical, 6)
    }
}

struct ContentImage: View {
    
    private let url: String
    
    init(url: String) {
        self.url = url
    }
    
    var body: some View {
        AsyncImage(urlString: url)
            .frame(height: 192)
            .frame(
                minWidth: 0,
                maxWidth: .infinity
            )
            .clipShape(RoundedRectangle(cornerRadius: 4))
            .padding(.horizontal, 24)
            .padding(.vertical, 6)
    }
}

struct ContentMap: View {
    
    private let id: String
    
    init(id: String) {
        self.id = id
    }
    
    var body: some View {
        AsyncImage(urlString: getUrlFromId(id: id))
            .frame(height: 248)
            .frame(
                minWidth: 0,
                maxWidth: .infinity
            )
            .clipShape(RoundedRectangle(cornerRadius: 4))
            .padding(.horizontal, 24)
            .padding(.vertical, 6)
    }
    
    func getUrlFromId(id: String) -> String {
        switch id {
            case "tropical_zone":
                return "./climate_zone_tropical_zone.png"

            case "tropical_and_subtropical_zone":
                 return "./climate_zone_tropical_and_subtropical_zone.png"

            case "temperate_zone":
                 return "./climate_zone_temperate_zone.png"

            case "subtropical_zone":
                 return "./climate_zone_subtropical_zone.png"

            case "polar_and_subpolar_zone":
                 return "./climate_zone_polar_and_subpolar_zone.png"

            default:
                return "./climate_zone_tropical_zone.png"
        }
    }
}

struct ContentFacts: View {
    
    private let facts: [Fact]
    
    init(facts: [Fact]) {
        self.facts = facts
    }
    
    var body: some View {
        ScrollView(.horizontal, showsIndicators: false) {
            HStack(spacing: 16) {
                ForEach(facts, id: \.self) { element in
                    
                    VStack(alignment: .center, spacing: 0) {
                        
                        Image(self.getIconAssetNameFromType(iconType: element.icon))
                            .resizable()
                            .frame(width: 48, height: 48)
                            .rotationEffect(element.icon == FactIconType.WIDTH ? .degrees(90) : .degrees(0))
                            .foregroundColor(MaterialTheme.colors.PRIMARY_VARIABLE)
                        
                        Text(element.text)
                            .modifier(MaterialTheme.typography.h3)
                            .padding(.top, 8)
                            .frame(
                                minWidth: 0,
                                maxWidth: .infinity
                            )
                        
                    }
                    .frame(width: 96, height: 96)
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
            .padding([.leading, .bottom, .trailing], 24)
        }
    }
    
    func getIconAssetNameFromType(iconType: FactIconType) -> String {
        switch iconType {
            case FactIconType.HEIGHT:
                return "baseline_height_black_24pt"
            case FactIconType.WIDTH:
                return "baseline_height_black_24pt"
            case FactIconType.THERMOSTAT:
                return "baseline_device_thermostat_black_24pt"
            case FactIconType.TRAVEL_EXPLORE:
                return "baseline_travel_explore_black_24pt"
        }
    }
}
