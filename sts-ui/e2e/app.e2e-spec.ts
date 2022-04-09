import { StsUiPage } from './app.po';

describe('sts-ui App', () => {
  let page: StsUiPage;

  beforeEach(() => {
    page = new StsUiPage();
  });

  it('should display welcome message', () => {
    page.navigateTo();
    expect(page.getParagraphText()).toEqual('Welcome to app!!');
  });
});
