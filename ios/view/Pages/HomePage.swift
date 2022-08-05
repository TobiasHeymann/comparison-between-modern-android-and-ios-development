import SwiftUI
import Combine

struct HomePage: View {
    
    @State private var availableNews: [News] = []
    @State private var availablePlants: [Plant] = []
    
    @State private var searchedTerm: String = ""
    @State private var searchedPlants: [Plant] = []
        
    var body: some View {
        ZStack(alignment: .leading) {
            MaterialTheme.colors.BACKGROUND.edgesIgnoringSafeArea(.all)
            
            ScrollView() {
                VStack(alignment: .leading) {
                    
                    SearchComponent(text: $searchedTerm)
                    
                    if searchedTerm.isEmpty {
                        HeaderComponent(header: "Pflanzen der Woche")
                        PlantListComponent(plants: availablePlants)
                        
                        HeaderComponent(header: "Neuigkeiten")
                        NewsListComponent(news: availableNews)
                    }else{
                        HeaderComponent(header: "Ergebnisse")
                        SearchResultListComponent(plants: searchedPlants)
                    }
                    
                    FooterComponent()
                }
            }
        }
        .navigationBarTitle("")
        .navigationBarHidden(true)
        .onAppear {
            self.downloadNews() { news in self.availableNews = news }
            self.downloadPlants() { plants in self.availablePlants = plants }
        }
        .onReceive(Just(searchedTerm)) { value in
            self.executeSearch(needle: value) { plants in self.searchedPlants = plants }
        }
    }
    
    func downloadNews(onResult: @escaping ([News]) -> ()) {
        DatabaseService.getLastTenNews { result in
            if let news = result {
                onResult(news)
            }
        }
    }
    
    func downloadPlants(onResult: @escaping ([Plant]) -> ()) {
        DatabaseService.getLastFivePlants { result in
            if let plants = result {
                onResult(plants)
            }
        }
    }
    
    func executeSearch(needle: String, onResult: @escaping ([Plant]) -> ()) {
        if needle.count > 2 {
            DatabaseService.getAllPlants() { plants in
                onResult( plants.filter { plant in plant.metadata.title.lowercased().starts(with: needle.lowercased()) })
            }
        } else {
            onResult([])
        }
    }
}

struct HomePage_Previews: PreviewProvider {
    static var previews: some View {
        HomePage()
    }
}
