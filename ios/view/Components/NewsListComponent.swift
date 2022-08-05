import SwiftUI

struct NewsListComponent: View {
    
    private let news: [News]
    
    init(news: [News]) {
        self.news = news
    }
    
    var body: some View {
        VStack(spacing: 16) {
            ForEach(news, id: \.self) { element in
                NewsComponent(news: element)
            }
        }.padding(.horizontal, 24)
    }
}

struct NewsComponent: View {
    
    private let news: News
    
    init(news: News) {
        self.news = news
    }
    
    var body: some View {
        NavigationLink(destination: NewsDetailPage(news: self.news)) {
            HStack(alignment: .top, spacing: 0) {
                AsyncImage(
                    urlString: news.metadata.image,
                    placeholder: UIImage(named: "placeholder_square")
                )
                .frame(width: 116, height: 116)
                .background(Color.red)
                .cornerRadius(4)
                
                VStack(alignment: .leading) {
                
                    Text(news.metadata.title)
                        .modifier(MaterialTheme.typography.h2)
                    
                    Text(format(from: news.metadata.timestamp))
                        .modifier(MaterialTheme.typography.subtitle1)
                    
                    Text(news.firstText)
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
    
    func format(from input: Date) -> String {
        let dateTimeFormatter = DateFormatter()
        dateTimeFormatter.dateFormat = "dd. MMMM yyyy"
        return dateTimeFormatter.string(from: input)
    }
}
