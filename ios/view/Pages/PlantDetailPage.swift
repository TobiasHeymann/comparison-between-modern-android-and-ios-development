import SwiftUI

struct PlantDetailPage: View {
    
    @Environment(\.presentationMode) var presentation
    
    private let plant: Plant
    
    init(plant: Plant) {
        self.plant = plant
    }
    
    var body: some View {
        ZStack(alignment: .leading) {
            MaterialTheme.colors.BACKGROUND.edgesIgnoringSafeArea(.all)
            
            ScrollView() {
                VStack(alignment: .leading) {
                    
                    ZStack(alignment: .topLeading) {
                        
                        AsyncImage(urlString: plant.metadata.image)
                            .frame(height: 256)
                            .frame(
                                minWidth: 0,
                                maxWidth: .infinity
                            )
                            .clipShape(Rectangle())
                        
                        VStack {
                            
                            Spacer(minLength: 232)
                                                        
                            Image("background_curve")
                                .frame(
                                    minWidth: 0,
                                    maxWidth: .infinity,
                                    alignment: .bottom
                                )
                                .foregroundColor(MaterialTheme.colors.BACKGROUND)
                        }
                        .frame(height: 256)
                        
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
                    }
                    
                    Text(plant.metadata.title)
                        .modifier(MaterialTheme.typography.h1)
                        .padding(.leading, 24)
                        .padding(.top, 12)
                        .padding(.trailing, 24)
                        .padding(.bottom, 2)
                                        
                    Text(plant.metadata.subtitle)
                        .modifier(MaterialTheme.typography.subtitle1)
                        .padding(.leading, 24)
                        .padding(.top, 2)
                        .padding(.trailing, 24)
                        .padding(.bottom, 24)
                    
                    ContentFacts(facts: plant.facts)
                    
                    ForEach(plant.content, id: \.self) { element in
                        Group {
                            if element.type == ContentType.HEADER {
                                ContentHeader(header: element.content)
                            } else if element.type == ContentType.TEXT {
                                ContentText(text: element.content)
                            } else if element.type == ContentType.IMAGE {
                                ContentImage(url: element.content)
                            } else if element.type == ContentType.MAP {
                                ContentMap(id: element.content)
                            }
                        }
                    }
                    
                    FooterComponent()
                }
            }
        }
        .navigationBarTitle("")
        .navigationBarHidden(true)
    }
}
