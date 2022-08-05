import Foundation

class DateService {
    static func toDate(from input: String) -> Date {
        
        let dateFormatter = DateFormatter()
        dateFormatter.timeZone = TimeZone(abbreviation: "UTC")
        dateFormatter.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'"
        
        if let output = dateFormatter.date(from: input) {
            return output
        } else {
            return Date.init()
        }
    }
}
