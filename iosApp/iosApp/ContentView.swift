import SharedLogic
import SwiftUI

struct ContentView: View {
    @ObservedObject private(set) var viewModel: ViewModel

    var body: some View {
        NavigationView {
            listView()
                .navigationBarTitle("Space Launches")
                .navigationBarItems(
                    trailing:
                        Button("Reload") {
                            self.viewModel.loadLaunches(forceReload: true)
                        }
                )
        }
    }

    private func listView() -> AnyView {
        switch viewModel.launches {
        case .loading:
            return AnyView(Text("Loading...").multilineTextAlignment(.center))
        case .result(let launches):
            return AnyView(
                List(launches) { launch in
                    RocketLaunchRow(rocketLaunch: launch)
                }
            )
        case .error(let description):
            return AnyView(Text(description).multilineTextAlignment(.center))
        }
    }
}

extension ContentView {
    enum LoadableLaunches {
        case loading
        case result([RocketLaunch])
        case error(String)
    }

    @MainActor
    class ViewModel: ObservableObject {
        @Published var launches = LoadableLaunches.loading
    }
}

extension RocketLaunch: Identifiable { }
