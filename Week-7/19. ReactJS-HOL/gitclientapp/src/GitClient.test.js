const axios = require("axios");
const GitClient = require("./GitClient");

jest.mock("axios");

describe("Git Client Tests", () => {
  test("should return repository names for techiesyed", async () => {
    const dummyRepos = [{ name: "Repo1" }, { name: "Repo2" }];
    axios.get.mockResolvedValue({ data: dummyRepos });

    const result = await GitClient.getRepositories("techiesyed");
    expect(result.data).toEqual(dummyRepos);
  });
});
