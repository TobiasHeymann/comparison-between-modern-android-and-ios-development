import SwiftUI

struct AsyncImage: View {
    @ObservedObject var asyncImageModel: AsyncImageModel
    var placeholder: UIImage?
    
    init(urlString: String?, placeholder: UIImage? = nil) {
        asyncImageModel = AsyncImageModel(urlString: urlString)
        self.placeholder = placeholder
    }
    
    var body: some View {
        Image(uiImage: asyncImageModel.image ?? placeholder ?? AsyncImage.defaultImage!)
            .resizable()
            .scaledToFill()
    }
    
    static var defaultImage = UIImage(named: "placeholder_square")
}

class AsyncImageModel: ObservableObject {
    @Published var image: UIImage?
    var urlString: String?
    
    init(urlString: String?) {
        self.urlString = urlString
        loadImage()
    }
    
    func loadImage() {
        loadImageFromUrl()
    }
    
    func loadImageFromUrl() {
        guard let urlString = urlString else {
            return
        }
        
        let url = URL(string: urlString)!
        let task = URLSession.shared.dataTask(with: url, completionHandler:
            getImageFromResponse(data:response:error:))
        task.resume()
    }
    
    
    func getImageFromResponse(data: Data?, response: URLResponse?, error: Error?)
    {
        guard error == nil else {
            print("Error: \(error!)")
            return
        }
        guard let data = data else {
            print("No data found")
            return
        }
        
        DispatchQueue.main.async {
            guard let loadedImage = UIImage(data: data) else {
                return
            }
            self.image = loadedImage
        }
    }
}
